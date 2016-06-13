package testgrp;

import co.paralleluniverse.fibers.FiberUtil;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.fibers.Suspendable;
import co.paralleluniverse.strands.Strand;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class OtherTest {
    interface I {
        void doSusp();
    }

    private static synchronized void syncM(I i) {
        i.doSusp();
    }

    @Test
    public void testSync() throws ExecutionException, InterruptedException {
        FiberUtil.runInFiber(() -> syncM(new I() {
            @Override
            @Suspendable
            public void doSusp() {
                try {
                    Strand.sleep(100);
                } catch (final SuspendExecution e) {
                    throw new AssertionError(e);
                } catch (final InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }
}
