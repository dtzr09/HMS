package database;

import model.Model;
import utils.exceptions.ModelAlreadyExistsException;
import utils.exceptions.ModelNotFoundException;
import utils.iocontrol.Savable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    /**
     * The list of model objects stored in the Database.
     */
    List<ModelObject> listOfModelObjects;

    /**
     * Creates a new instance of the Database class.
     */
    public Database() {
        super();
        listOfModelObjects = new ArrayList<>();
    }

    /**
     * Gets the path of the Database file.
     *
     * @return the path of the Database file
     */
    public abstract String getFilePath();

    /**
     * Gets the list of mappable objects.
     *
     * @return the list of mappable objects
     */
    @Override
    protected List<ModelObject> getAll() {
        return listOfModelObjects;
    }

    /**
     * Gets a model object by ID
     *
     * @param modelObjectID the ID of the model object to get
     * @return the model object with the given ID
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
        if (contains(modelObject.getModelID())) {
            throw new ModelAlreadyExistsException(
                    "A model object with ID " + modelObject.getModelID() + " already exists.");
        } else {
            listOfModelObjects.add(modelObject);
            save(getFilePath());
        }
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
     * Gets the size of the Database.
     *
     * @return the size of the Database
     */
    public int size() {
        return listOfModelObjects.size();
    }

    /**
     * Removes all model objects from this Database.
     */
    public void clear() {
        listOfModelObjects.clear();
        save(getFilePath());
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

    /**
     * Finds all model objects in the Database that match the specified rules.
     * <p>
     * Multiple rules can be specified, and all rules must be satisfied for a model
     * object to be considered a match.
     * <p>
     * The rules are specified as lambda expressions that take a model object as a
     * parameter and return a boolean.
     * <p>
     * Here is an example of how to use this method:
     *
     * <pre>
     * List&lt;Student&gt; modelObjects = Database.findByRules(
     *         student -&gt; student.getFirstName().equals("John"),
     *         student -&gt; student.getLastName().equals("Smith"));
     * </pre>
     * <p>
     * This will return a list of all students whose first name is "John" and whose
     * last name is "Smith".
     *
     * @param rules the rules to match
     * @return a list of all model objects in the Database that match the
     *         specified rules
     */
    @SafeVarargs
    public final List<ModelObject> findByRules(DatabaseRule<ModelObject>... rules) {
        List<ModelObject> modelObjects = new ArrayList<>();
        for (ModelObject modelObject : listOfModelObjects) {
            boolean isMatch = true;
            for (DatabaseRule<ModelObject> rule : rules) {
                if (!rule.isMatch(modelObject)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                modelObjects.add(modelObject);
            }
        }
        return modelObjects;
    }

    /**
     * Gets a list of all model objects in the Database.
     *
     * @return a list of all model objects in the Database
     */
    public List<ModelObject> getList() {
        return findByRules();
    }

    /**
     * Provides a rule for filtering model objects in the Database.
     *
     * @param <ModelObject> the type of model object stored in the Database
     */
    public interface DatabaseRule<ModelObject> {
        /**
         * Checks whether the specified model object matches the rule.
         *
         * @param modelObject the model object to check
         * @return true if the model object matches the rule, false otherwise
         */
        boolean isMatch(ModelObject modelObject);
    }
}
