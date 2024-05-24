package kirdin.lab.catMs.handlers.massageHandlers;

public interface IMessageHandler {
    public IMessageHandler setNext(IMessageHandler handler);
    public void handle(String key, Object value);
}
