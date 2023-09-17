import static org.junit.Assert.*;

import com.todone.parser.Parser;
import org.junit.Test;
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
}
