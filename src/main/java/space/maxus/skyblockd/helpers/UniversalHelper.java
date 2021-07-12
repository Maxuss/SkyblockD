package space.maxus.skyblockd.helpers;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UniversalHelper {
    public static <T> List<T> filter(List<T> input, Predicate<? super T> filter){
        return input.stream().filter(filter).collect(Collectors.toList());
    }

    public static <T> Type getType(T in){
        return new TypeToken<T>(){}.getType();
    }
}
