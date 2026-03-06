package misato.tasks;

import misato.exceptions.MisatoException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Deadline extends Task {
    protected LocalDateTime by;

    //Use 'uuuu' instead of 'yyyy' and chain .withResolverStyle(ResolverStyle.STRICT)
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter
            .ofPattern("uuuu-MM-dd HHmm")
            .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");

    public Deadline(String description, String byStr) throws MisatoException {
        super(description);
        try {
            this.by = LocalDateTime.parse(byStr.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            //error message to clarify that the date might not exist
            throw new MisatoException("Your date format or value is invalid. Use: yyyy-MM-dd HHmm (e.g., 2026-12-31 1800). Ensure the date actually exists!");
        }
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}