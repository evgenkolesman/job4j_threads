package forconcurrency.servlet;

import net.jcip.annotations.ThreadSafe;
import org.apache.commons.lang3.concurrent.Computable;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

@ThreadSafe
public class Factorized implements Servlet {
    private final Computable<BigInteger, BigInteger[]> computable = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }
    };

    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(computable);


    @Override
    public void service(ServletRequest req,
                        ServletResponse res) throws ServletException, IOException {
        try {
            BigInteger i = extractFromReq(req);
            encodeIntoResp(res, cache.compute(i));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

    private void encodeIntoResp(ServletResponse res, BigInteger[] compute) {
    }

    private BigInteger extractFromReq(ServletRequest req) {
        return null;
    }

    private BigInteger[] factor(BigInteger arg) {
        return null;
    }
}
