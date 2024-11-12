package database;

import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.iocontrol.Savable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Model;

/**
 * The Database abstract class provides the basic functionality for storing,
 * retrieving, and managing a list of model objects.
 * It implements the Savable interface and provides methods for adding,
 * removing, updating, and finding model objects.
 *
 * @param <ModelObject> the type of model object stored in the Database
 */
public abstract class Database<ModelObject extends Model> extends Savable<ModelObject>
        implements Iterable<ModelObject> {

    List<ModelObject> listOfModelObjects;

    public Database() {
        super();
        listOfModelObjects = new ArrayList<>();
    }

    public abstract String getFilePath();

    @Override
    protected List<ModelObject> getAll() {
        return listOfModelObjects;
    }

    /**
     * Gets a model object by email.
     * 
     * @param userEmail
     * @return
     * @throws ModelNotFoundException
     */
    public ModelObject getByEmail(String userEmail) throws ModelNotFoundException {
        // Only users will have email
        for (ModelObject modelObject : listOfModelObjects) {
            if (modelObject.getModelEmail().equalsIgnoreCase(userEmail)) {
                return modelObject;
            }
        }
        throw new ModelNotFoundException("No model object with Email" + userEmail + " exists.");
    }

    /**
     * Gets a model object by ID.
     *
     * @param modelObjectID the ID of the model object to get
     * @return the model object with the specified ID
     * @throws ModelNotFoundException if the model object with the given ID does not
     *                                exist
     */
    public ModelObject getByID(String modelObjectID) throws ModelNotFoundException {
        for (ModelObject modelObject : listOfModelObjects) {
            if (modelObject.getModelID().equalsIgnoreCase(modelObjectID)) {
                return modelObject;
            }
        }
        throw new ModelNotFoundException("No model object with ID " + modelObjectID + " exists.");
    }

    /**
     * Checks whether the Database contains a model object with the given ID.
     *
     * @param modelObjectID the ID of the model object to check
     * @return true if the Database contains a model object with the given ID,
     *         false otherwise
     */
    public boolean contains(String modelObjectID) {
        try {
            getByID(modelObjectID);
            return true;
        } catch (ModelNotFoundException e) {
            return false;
        }
    }

    /**
     * Adds a model object to the Database.
     *
     * @param modelObject the model object to add
     * @throws ModelAlreadyExistsException if a model object with the same ID
     *                                     already exists in the Database
     */
    public void add(ModelObject modelObject) throws ModelAlreadyExistsException {
        if (contains(modelObject.getModelID()) || contains(modelObject.getModelEmail())) {
            throw new ModelAlreadyExistsException(
                    "A model object with ID " + modelObject.getModelID() + " or email " + modelObject.getModelEmail()
                            + " already exists.");
        } else {
            listOfModelObjects.add(modelObject);
            save(getFilePath());
        }
    }

    /**
     * Updates the specified model object in the Database.
     *
     * @param modelObject the model object to update
     * @throws ModelNotFoundException if the specified model object is not found in
     *                                the Database
     */
    public void update(ModelObject modelObject) throws ModelNotFoundException {
        ModelObject oldModelObject = getByID(modelObject.getModelID());
        listOfModelObjects.set(listOfModelObjects.indexOf(oldModelObject), modelObject);
        save(getFilePath());
    }

    /**
     * Removes a model object from the Database by ID.
     *
     * @param modelObjectID the ID of the model object to remove
     * @throws ModelNotFoundException if the model object with the given ID does not
     *                                exist
     */
    public void remove(String modelObjectID) throws ModelNotFoundException {
        listOfModelObjects.remove(getByID(modelObjectID));
        save(getFilePath());
    }

    /**
     * Checks whether the Database is empty.
     *
     * @return true if the Database is empty, false otherwise
     */
    public boolean isEmpty() {
        return listOfModelObjects.isEmpty();
    }

    /**
     * Removes all model objects from this Database.
     */
    public void clear() {
        listOfModelObjects.clear();
        save(getFilePath());
    }

    /**
     * Updates all model objects in the Database with the specified list of model
     * objects.
     *
     * @param modelObjects the list of model objects to update
     */
    public void updateAll(List<ModelObject> modelObjects) {
        listOfModelObjects = modelObjects;
        save(getFilePath());
    }

    /**
     * Loads the list of model objects from the Database file.
     */
    public void load() {
        this.listOfModelObjects = new ArrayList<>();
        load(getFilePath());
    }

    /**
     * Saves the list of model objects to the Database file.
     */
    public void save() {
        save(getFilePath());
    }

    /**
     * Returns an iterator over the list of model objects of type {@code T}.
     *
     * @return an iterator over the list of model objects
     */
    @Override
    public Iterator<ModelObject> iterator() {
        return listOfModelObjects.iterator();
    }
}
