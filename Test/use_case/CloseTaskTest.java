package use_case;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.close_task.*;

import static org.junit.Assert.*;

public class CloseTaskTest {

    private CloseTaskDataAccessInterface closeTaskDataAccessObject;
    private CloseTaskOutputBoundary successPresenter;

    @Before
    public void setUp() {
        closeTaskDataAccessObject = Mockito.mock(CloseTaskDataAccessInterface.class);
        // 模拟关闭任务的行为
        Mockito.doNothing().when(closeTaskDataAccessObject).closeTask(Mockito.anyString());

        successPresenter = new CloseTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(CloseTaskOutputData outputData) {
                assertTrue(outputData.getTaskStatus());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };
    }

    @Test
    public void successTest() {
        // 创建一个关闭任务的输入数据实例
        CloseTaskInputData inputData = new CloseTaskInputData("taskId123");

        // 创建交互器实例，并传入模拟的数据访问对象和成功时的展示逻辑
        CloseTaskInputBoundary interactor = new CloseTaskInteractor(successPresenter, closeTaskDataAccessObject);

        // 执行关闭任务的操作
        interactor.execute(inputData);

        // 验证是否调用了closeTask方法
        Mockito.verify(closeTaskDataAccessObject).closeTask("taskId123");
    }
}
