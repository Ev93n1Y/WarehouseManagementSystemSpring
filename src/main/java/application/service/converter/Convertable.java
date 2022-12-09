package application.service.converter;

public interface Convertable<T, A> {
    T toDto(A a);

    A toDao(T t);
}
