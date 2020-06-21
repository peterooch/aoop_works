package components.factories;

import utilities.VehicleType;
/** Vehicle Factory interface
 * @author Baruch Rutman, ID 206119109, Campus Be'er Sheva
 */
public interface VehicleFactory {
    /**
     * Vehicle supplier
     * @param type vehicle type
     * @return <code>vehicleType</code> object
     */
    public VehicleType getVehicle(String type);
}