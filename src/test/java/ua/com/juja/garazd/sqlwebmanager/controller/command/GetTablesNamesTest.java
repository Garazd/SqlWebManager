package ua.com.juja.garazd.sqlwebmanager.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.garazd.sqlwebmanager.model.DatabaseManager;
import ua.com.juja.garazd.sqlwebmanager.view.View;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GetTablesNamesTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new GetTablesNames(manager, view);
    }

    @Test
    public void testCanProcessGetTablesNamesParametersString() {
        // given

        // when
        boolean canProcess = command.canProcess("show");

        // then
        assertTrue(canProcess);
    }

    @Test
    public void testCanProcessGetTablesNamesWithoutParametersString() {
        // given

        // when
        boolean canProcess = command.canProcess("");

        // then
        assertFalse(canProcess);
    }

    @Test
    public void testCanProcessQweString() {
        // given

        // when
        boolean canProcess = command.canProcess("show|user");

        // then
        assertFalse(canProcess);
    }

    @Test
    public void testProcessCommandGetTablesNames() {
        // given

        // when
        command.process("show");

        // then
        verify(view).write("[]");
    }
}