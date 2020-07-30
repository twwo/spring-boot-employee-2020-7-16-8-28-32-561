package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.exception.NotSuchDataException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class NotSuchDataExceptionTest {
    @Test
    void should_return_not_such_data_when_delete_employee_given_wrong_id() {
        //given
        EmployeeRepository mockedEmployeeRepository = mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Throwable exception = assertThrows(NotSuchDataException.class,
                () -> employeeService.deleteEmployee(1));

        //then
        assertEquals(NotSuchDataException.class, exception.getClass());
    }
}
