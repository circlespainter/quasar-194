package testgrp;

import co.paralleluniverse.fibers.FiberUtil;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.SuspendableRunnable;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class OtherTest {
    private static synchronized void syncM() throws InterruptedException, SuspendExecution {
        Strand.sleep(100);
    }

    @Test
    public void testSync() throws ExecutionException, InterruptedException {
        FiberUtil.runInFiber(new SuspendableRunnable() {
            @Override
            public void run() throws SuspendExecution, InterruptedException {
                syncM();
            }
        });
    }
}
