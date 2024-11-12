package model;

import utils.iocontrol.Mappable;

/**
 * 
 * The Model interface represents an entity that can be stored in the
 * application's data storage.
 * It extends the Mappable interface, which allows it to be serialized and
 * deserialized as a map of key-value pairs.
 * The interface requires implementations to provide a unique ID string and
 * defines how the ID can be retrieved.
 */
public interface Model extends Mappable {
    /**
     * Retrieves the unique identifier for the model entity.
     * 
     * @return the unique ID of the model.
     */
    String getModelID();

    /**
     * Retrieves the model email associated with the entity, if available.
     * This method has a default implementation returning null,
     * allowing optional override by implementing classes.
     * 
     * @return the model email, or null if not applicable.
     */
    default String getModelEmail() {
        return null;
    }
}
