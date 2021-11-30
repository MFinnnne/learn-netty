package c01;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import net.MyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Promise04 {

    private final static Logger log = LoggerFactory.getLogger(MyServer.class);
    public static void main(String[] args) {
        DefaultEventLoop eventExecutors = new DefaultEventLoop();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventExecutors);

        promise.addListener(future -> {
            log.debug("result {}", (promise.isSuccess() ? promise.getNow() : promise.cause()).toString());
            eventExecutors.shutdownGracefully();
        });

        eventExecutors.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RuntimeException e = new RuntimeException("error...");
            log.debug("set failure, {}", e.toString());
            promise.setFailure(e);
        });

        log.debug("start...");
    }
}
