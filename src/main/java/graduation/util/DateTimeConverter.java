package graduation.util;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeConverter
        implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return Timestamp.from(attribute.toInstant(ZoneOffset.UTC));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        if (dbData != null) {
            LocalDateTime localDateTime2 = dbData.toLocalDateTime();
            return LocalDateTime.ofInstant(dbData.toInstant(), ZoneOffset.UTC);
        }
        return null;
    }
}
