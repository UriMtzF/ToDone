import static org.junit.Assert.*;

import com.todone.parser.Parser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {
    Parser parser = new Parser();
    @Test
    public void testIsValidTask(){
        // Task must start with "-" and followed by and space
        assertTrue(this.parser.isValidTask("- This is a task"));
        // "-" isn't followed by a space
        assertFalse(this.parser.isValidTask("-This isn't a task"));
        // Task doesn't start with "-"
        assertFalse(this.parser.isValidTask("+ This isn't a task"));
        // Task doesn't start with "-"
        assertFalse(this.parser.isValidTask("This isn't a task"));
    }
    @Test
    public void testIsValidDate(){
        // Date must have the "yyyy-MM-dd" style
        assertTrue(this.parser.isValidDate("2023-09-17"));
        // Date doesn't have a valid month
        assertFalse(this.parser.isValidDate("2023-00-01"));
        assertFalse(this.parser.isValidDate("2023-13-01"));
        // Date doesn't have a valid day
        assertFalse(this.parser.isValidDate("2023-01-00"));
        assertFalse(this.parser.isValidDate("2023-01-32"));
        // Date must be existent
        assertTrue(this.parser.isValidDate("2020-02-29"));
        assertFalse(this.parser.isValidDate("2023-02-29"));
    }
    @Test
    public void testDoneDate(){
        // Done date must be started by "done:" and followed by a valid date separated by a space
        assertEquals("2023-09-17",this.parser.parseDoneDate("Task with done:2023-09-17 date"));
        assertEquals("",this.parser.parseDoneDate("Task withdone:2023-09-17 date"));
        // Done date must have a valid date
        assertEquals("",this.parser.parseDoneDate("Task with invalid done:2023-13-17 date"));
        // If done isn't present it should return an empty String
        assertEquals("",this.parser.parseDoneDate("Task without a done date"));
    }
    @Test
    public void testCreationDate(){
        // Created date must be started by "created:" and followed by a valid date separated by a space
        assertEquals("2023-09-17",this.parser.parseCreationDate("Task created:2023-09-17"));
        assertEquals("",this.parser.parseCreationDate("Task withcreated:2023-09-17 date"));
        // Created date must have a valid date
        assertEquals("",this.parser.parseCreationDate("Task with invalid created:2023-13-17 date"));
        // If creation isn't present it should return an empty String
        assertEquals("",this.parser.parseCreationDate("Task without a creation date"));
    }
    @Test
    public void testDueDate(){
        // Due date must be started by "due:" and followed by a valid date separated by a space
        assertEquals("2023-09-17",this.parser.parseCreationDate("Task due:2023-09-17"));
        assertEquals("",this.parser.parseCreationDate("Task withdue:2023-09-17 date"));
        // Due date must have a valid date
        assertEquals("",this.parser.parseCreationDate("Task with invalid due:2023-13-17 date"));
        // If due isn't present it should return an empty String
        assertEquals("",this.parser.parseCreationDate("Task without a due date"));
    }
    @Test
    public void testPriorityParser(){
        // Priority must be only one capital letter surrounded by "()" and separated by a space
        assertEquals("A",this.parser.parsePriority("(A) It has a first priority"));
        assertEquals("",this.parser.parsePriority("(a) It doesn't have priority"));
        assertEquals("",this.parser.parsePriority("(A)It has a first priority"));
        // If a parentheses is found it should return an empty String
        assertEquals("",this.parser.parsePriority("It doesn't have priority (It's a secret)"));
    }
    @Test
    public void testProjectParser(){
        List<String> noProjectFound = new ArrayList<>();
        List<String> oneProjectFound = new ArrayList<>();
        oneProjectFound.add("School");
        List<String> multipleProjectsFound = new ArrayList<>();
        multipleProjectsFound.add("School");
        multipleProjectsFound.add("Home");
        multipleProjectsFound.add("I_love_tasks");
        // Projects must be started by a "+" sign and followed by any String
        // Before the "+" is important to have a space
        // the name of the project ends until it finds a space
        // Multiple projects can be written
        assertEquals(oneProjectFound,this.parser.parseProjects("This have a +School project"));
        assertEquals(multipleProjectsFound,this.parser.parseProjects("This have a +School project done in +Home +I_love_tasks"));
        // If a project doesn't have a starting space it isn't a project
        assertEquals(noProjectFound,this.parser.parseProjects("I should operate 5+twenty_two"));
        // If no project is found it should return an empty List
        assertEquals(noProjectFound,this.parser.parseProjects("This doesn't have projects"));
    }
    @Test
    public void testTagParser(){
        List<String> noTagFound = new ArrayList<>();
        List<String> oneTagFound = new ArrayList<>();
        oneTagFound.add("School");
        List<String> multipleTagsFound = new ArrayList<>();
        multipleTagsFound.add("School");
        multipleTagsFound.add("Home");
        multipleTagsFound.add("I_love_tasks");
        // Tags must be started by a "@" sign and followed by any String
        // Before the "@" is important to have a space
        // the name of the tag ends until it finds a space
        // Multiple tags can be written
        assertEquals(oneTagFound,this.parser.parseTags("This have a @School tag"));
        assertEquals(multipleTagsFound,this.parser.parseTags("This have a @School tag done in @Home @I_love_tasks"));
        // If a tag doesn't have a starting space it isn't a tag
        assertEquals(noTagFound,this.parser.parseTags("I should send an email to hello@example.com"));
        // If no project is found it should return an empty List
        assertEquals(noTagFound,this.parser.parseTags("This doesn't have tags"));
    }
}
