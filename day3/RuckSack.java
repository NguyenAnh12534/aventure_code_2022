package day3;

import java.util.List;

public class RuckSack {
    private Compartment[] compartments = new Compartment[2];

    public RuckSack(String ruckSackContent) {
        initiateCompartments(ruckSackContent);
    }

    private void initiateCompartments(String ruckSackContent) {
        compartments[0] = new Compartment(ruckSackContent.substring(0, ruckSackContent.length() / 2 - 1));
        compartments[1] = new Compartment(ruckSackContent.substring(ruckSackContent.length() / 2,  ruckSackContent.length()-1));
    }

    public int calculateDuplicatedElements() {
        String firstCompartmentContent = compartments[0].value;
        String secondCompartmentContent = compartments[1].value;

        int[] items = new int[127];
        for(byte item : firstCompartmentContent.getBytes()) {
            items[item]++;
        }

        for(byte item : secondCompartmentContent.getBytes()) {
            items[item]++;
        }

        
    }

    private class Compartment {
        private String value;

        public Compartment(String value) {
            this.value = value;
        }
    }

}
