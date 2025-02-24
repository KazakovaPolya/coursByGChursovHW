import java.text.DecimalFormat;

/**
 * Class for imitating a work of delivery service with calculating a final cost by delivery parameters
 */
public class Delivery {
    public static final double MINIMUM_DELIVERY_AMOUNT = 400;

    private final int destinationDistance;
    private final boolean isBigDimansions;
    private final boolean isFragile;
    private enum deliveryServiceWorkload{
        VERY_HIGHT,
        HIGHT,
        LOW_HIGHT
    };
    private final String deliveryRate;

    public Delivery(int destinationDistance, boolean isBigDimansions, boolean isFragile, String deliveryRate) {
        this.destinationDistance = destinationDistance;
        this.isBigDimansions = isBigDimansions;
        this.isFragile = isFragile;
        this.deliveryRate = deliveryRate;
    }

    /**
     * Returns delivery cost or an error, if there are wrong input
     * @return calculated cost or default if calculated is less than 400
     */
    public double calculateDeliveryCost() {
        if (this.isFragile && this.destinationDistance > 30)
            throw new UnsupportedOperationException("Fragile cargo cannot be delivered for the distance more than 30");

        double calculatedDeliveryCost = (getDestinationDistanceCostIncrease(this.destinationDistance) + getBigDimansionsCostIncrease(this.isBigDimansions) + getFragileCostIncrease(this.isFragile)) * getDeliveryRate(this.deliveryRate);
        DecimalFormat df = new DecimalFormat("###");
        return Math.max(Double.parseDouble(df.format(calculatedDeliveryCost)), MINIMUM_DELIVERY_AMOUNT);
    }

    /**
     * Returns additional cost based on the destination distance
     * @param destinationDistance - distance to the target
     * @return calculated cost
     */
    private int getDestinationDistanceCostIncrease(int destinationDistance) {
        if (destinationDistance > 30) return 300;
        if (destinationDistance > 10) return 200;
        if (destinationDistance > 2) return 100;
        if (destinationDistance >= 0) return 50;
        throw new IllegalArgumentException("destinationDistance should be a positive number!");
    }

    /**
     * Returns additional cost based on the fragility of the item
     * @param isFragile - is item fragile or not
     * @return calculated cost, zero for a not fragile item
     */
    private int getFragileCostIncrease(boolean isFragile) {
        return isFragile ? 300 : 0;
    }

    private int getBigDimansionsCostIncrease(boolean isBigDimansions){
        return isBigDimansions ? 200 : 100;
    }

    private double getDeliveryRate(String deliveryRate){
        if(deliveryRate.equals(deliveryServiceWorkload.VERY_HIGHT.name()))
            return 1.6;
        if (deliveryRate.equals(deliveryServiceWorkload.HIGHT.name()))
            return 1.4;
        if (deliveryRate.equals(deliveryServiceWorkload.LOW_HIGHT.name()))
            return 1.2;
        return 1;
    }
}