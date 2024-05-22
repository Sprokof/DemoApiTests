package api.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Map;
import java.util.stream.Stream;

public class FakeStoreIdValidationProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                new FakeStoreIdValidationHolder(
                        Map.of("id", "1"),
                        200,
                        true
                ),
                new FakeStoreIdValidationHolder(
                        Map.of("id", "0d0"),
                        404,
                        false
                )
        ).map(Arguments::of);
    }
}
