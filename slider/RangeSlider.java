package slider;

import javax.swing.JSlider;

/**
 * An extension of JSlider with an additional marker, which allows to select
 * ranges of values.
 * 
 * Note that RangeSlider makes use of the default BoundedRangeModel, which 
 * supports an inner range defined by a value and an extent.  The upper value
 * returned by RangeSlider is simply the lower value plus the extent.
 * 
 * @author Jesus Cuenca - jesus.cuenca@gmail.com
 */
public class RangeSlider extends JSlider {

	private static final long serialVersionUID = -471709432705473937L;

    public RangeSlider() {
        initSlider();
    }

    public RangeSlider(int min, int max) {
        super(min, max);
        initSlider();
    }

    private void initSlider() {
        setOrientation(HORIZONTAL);
    }

    @Override
    public void updateUI() {
        setUI(new RangeSliderUI(this));
        // Update UI for slider labels.  This must be called after updating the
        // UI of the slider.  Refer to JSlider.updateUI().
        updateLabelUIs();
    }

    @Override
    public int getValue() {
        return super.getValue();
    }

    @Override
    public void setValue(int value) {
        int oldValue = getValue();
        if (oldValue == value) {
            return;
        }

        // Compute new value and extent to maintain keep value.
        int oldExtent = getExtent();
        int newValue = Math.min(Math.max(getMinimum(), value), oldValue + oldExtent);
        int newExtent = oldExtent + oldValue - newValue;

        getModel().setRangeProperties(newValue, newExtent, getMinimum(), 
            getMaximum(), getValueIsAdjusting());
    }

    public int getUpperValue() {
        return getValue() + getExtent();
    }

    public void setUpperValue(int value) {
        // Compute new extent.
        int lowerValue = getValue();
        int newExtent = Math.min(Math.max(0, value - lowerValue), getMaximum() - lowerValue);
        
        // Set extent in order to set upper value.
        setExtent(newExtent);
    }
}
