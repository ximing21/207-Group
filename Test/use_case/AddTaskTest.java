package use_case;

import entity.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.add_project.*;
import use_case.add_task.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddTaskTest {

    private AddTaskDataAccessInterface addTaskDataAccessObject;
    private AddTaskOutputBoundary successPresenter;

    @Before
    public void setUp() {
        addTaskDataAccessObject = Mockito.mock(AddTaskDataAccessInterface.class);

        Mockito.when(addTaskDataAccessObject.addTask(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn("mockedTaskId");

        successPresenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData task) {
                // 改为检查传入的任务名称是否正确
                assertEquals("Write Test Cases", task.getName());
                assertEquals("2023-12-15", task.getdeadline());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
    }

    @Test
    public void successTest() {
        // 创建一个任务输入数据实例
        AddTaskInputData inputData = new AddTaskInputData("Write Test Cases", "CSC207", "2023-12-15");

        // 创建交互器实例，并传入模拟的数据访问对象和成功时的展示逻辑
        AddTaskInputBoundary interactor = new AddTaskInteractor(successPresenter, addTaskDataAccessObject);

        // 执行添加任务的操作
        interactor.execute(inputData);

        // 验证是否调用了addTask方法并检查传入的参数
        Mockito.verify(addTaskDataAccessObject).addTask("Write Test Cases", "CSC207", "2023-12-15");
    }
}


