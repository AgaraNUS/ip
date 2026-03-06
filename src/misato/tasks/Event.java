package misato.tasks;

import misato.exceptions.MisatoException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle; // NEW IMPORT

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter
            .ofPattern("uuuu-MM-dd HHmm")
            .withResolverStyle(ResolverStyle.STRICT);

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");

    public Event(String description, String fromStr, String toStr) throws MisatoException {
        super(description);
        try {
            this.from = LocalDateTime.parse(fromStr.trim(), INPUT_FORMAT);
            this.to = LocalDateTime.parse(toStr.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new MisatoException("Your date format or value is invalid. Use: yyyy-MM-dd HHmm (e.g., 2026-12-31 1800). Ensure the date actually exists!");
        }
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}