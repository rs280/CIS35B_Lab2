package model;

public class OptionSet implements java.io.Serializable {
    private static final long serialVersionUID = 5846223453457830887L;
    private Option optionList[];
    private String optionSetName;
    private int optionListLength;

    /*
     * Constructor
     */
    protected OptionSet() {
        /*
         * We don't know the size so let's just make it size 12 and resize it later if
         * need be It would have been nice to use a List<>
         */
        int size = 12;
        optionList = new Option[size];
        optionSetName = "";
        optionListLength = 0;
        for (int i = 0; i < size; i++)
            optionList[i] = new Option();
    }

    protected OptionSet(String name, int size) {
        optionList = new Option[size];
        optionSetName = name;
        optionListLength = 0;
        for (int i = 0; i < size; i++)
            optionList[i] = new Option();
    }

    /*
     * Getter
     */
    protected String getName() {
        return optionSetName;
    }

    // Get OptionSet (by index value)
    protected Option getOption(int index) {
        Option optionObject = null;
        try {
            optionObject = optionList[index];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionObject;
    }

    protected int length() {
        return optionListLength;
    }

    /*
     * Find
     */
    // Find Option with name (in context of OptionSet)
    protected Option findOptionSet(String name) {
        Option optionObject = null;
        for (int i = 0; i < optionList.length; i++) {
            try {
                if (optionList[i].getName() == name) {
                    optionObject = optionList[i];
                }
            } catch (NullPointerException e) {
                /*
                 * According to Carnegie Mellon University Software Engineering Institute You
                 * should not catch a null pointer exception. BUT WE WILL FOR THE SAKE OF THE
                 * ASSIGNMENT!
                 */
                break;
            }
        }
        return optionObject;
    }

    /*
     * Setter
     */
    protected void setName(String name) {
        optionSetName = name;
    }

    // Set values of Option (in context of OptionSet)
    protected int setOption(String name, double price_) {
        Option optionObject = getOption(optionListLength);
        optionObject.setName(name);
        optionObject.setPrice(price_);
        return optionListLength++;
    }

    /*
     * print() and toString()
     */
    public void print() {
        System.out.print(toString());
    }

    public String toString() {
        StringBuffer stringBufferObject;
        int i, n;
        n = length();
        stringBufferObject = new StringBuffer("");
        stringBufferObject.append(getName()).append(": ");
        for (i = 0; i < n; i++) {
            stringBufferObject.append(getOption(i).toString());
            if (i < n - 1) {
                stringBufferObject.append(", ");
            }
        }
        return stringBufferObject.toString();
    }
}
