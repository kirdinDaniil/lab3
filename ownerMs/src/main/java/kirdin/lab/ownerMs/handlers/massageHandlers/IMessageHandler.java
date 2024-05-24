package kirdin.lab.ownerMs.handlers.massageHandlers;

public interface IMessageHandler {
    public IMessageHandler setNext(IMessageHandler handler);
    public void handle(String key, Object value);
}
