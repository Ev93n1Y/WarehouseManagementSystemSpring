package application.service.converter;

public interface Convertible<T, A> {
    T toDto(A a);

    A toDao(T t);
}
