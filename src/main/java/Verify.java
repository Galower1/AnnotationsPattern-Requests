import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = Verifiable.class)
public @interface Verify {
    String[] header();
}