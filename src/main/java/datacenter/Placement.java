package datacenter;

import java.util.ArrayList;
import java.util.List;

public class Placement {
    private List<Processor> processors;

    /** Create a new empty placement
     *
     */
    public Placement() {
        this.processors = new ArrayList<Processor>();
    }

    /** Add a processor to this placement
     *
     */
    public void addProcessor(Processor processor) {
        this.processors.add(processor);
    }

    /** Get the cost of this placement
     *
     *  @return cost
     */
    public int getCost() {
        int totalCost = 0;
        for(Processor processor: processors){
            totalCost += processor.getPeakMemoryUsage();
        }
        return totalCost;
    }

    /**
     * Compute the makespan of this placement
     *
     * @return the makespan of the placement, and return 0, if there is no work
     * (no processors or no jobs on any processor)
     */
    public int getMakeSpan() {
        int makeSpan = 0;
        for(Processor processor: processors){
            makeSpan = Math.max(makeSpan, processor.getTotalComputationTime());
        }
        return makeSpan;
    }

    /** Check if this placement is equal to another given placement
     *
     * @param that is the other placement to check
     * @return true if (1) number of processors in "that" is
     * equal to the number of processors in "this", and (2) each processor
     * in "this" is equal to the corresponding processor in "that"
     * (order of processors does matter)
     * */
    public boolean equals(Placement that) {
        if(that == null){
            return false;
        }
        if(this.processors.size() != that.processors.size()){
            return false;
        }
        for(int i = 0; i <this.processors.size(); i++){
            if(!this.processors.get(i).equals(that.processors.get(i))){
                return false;
            }
        }
        return true;
    }

    /** Obtain the mean flow time for this placement
     *
     * @return the mean flow time, and return 0 if there is no work (no processors or no jobs on any processor)
     */
    public double getMeanFlowTime() {
        // TODO: Implement this method
        return -1;
    }

    /** Obtain the median flow time for this placement
     *
     * @return the median flow time, and return 0 if there is no work (no processors or no jobs on any processors)
     */
    public double getMedianFlowTime() {
        // TODO: Implement this method
        return -1;
    }

}