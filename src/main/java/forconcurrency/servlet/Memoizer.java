package forconcurrency.servlet;

import org.apache.commons.lang3.concurrent.Computable;

import java.util.Map;
import java.util.concurrent.*;


public class Memoizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> comp;

    public Memoizer(Computable<A, V> comp) {
        this.comp = comp;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> eval = new Callable<>() {
                    @Override
                    public V call() throws InterruptedException {
                        return comp.compute(arg);
                    }
                };
                FutureTask<V> futureTask = new FutureTask<>(eval);
                future = cache.putIfAbsent(arg, futureTask);
                if (future == null) {
                    futureTask.run();
                    future = futureTask;
                }
            }

            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(arg, future);
            } catch (ExecutionException e) {
                throw new InterruptedException();
            }
        }
    }
}
