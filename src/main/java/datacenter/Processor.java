package datacenter;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Processor {

    /*
        Abstraction Function:
            this represents a processor with time capacity this.timeLimit
            and holds the jobs in this.jobs
        Representation Invariant:
            jobs != null && jobs does not contain nulls
            && timeLimit > 0
            && (there should be something more that you should think about)
     */

    private List<Job> jobs;
    private int timeLimit;

    /**
     * Create a new empty processor
     * @param timeLimit the limit on compute time on this processor, > 0
     */
    public Processor(int timeLimit) {
        this.timeLimit = timeLimit;
        this.jobs = new ArrayList<Job>();
    }

    /**
     * Check if a given job can fit in this processor
     *
     * @return true if adding the job does not exceed the time limit on this processor, and false otherwise.
     */
    public boolean canFitJob(Job job) {
        return (getTotalComputationTime() + job.getExecutionTime()) <= timeLimit;
    }

    /** Inserts a job to the processor, at the end of its schedule
     *
     * @param job not null
     * @return true if the job can fit on this processor and was assigned, and false otherwise
     */
    public boolean addJob(Job job) {
        if(canFitJob(job)){
            jobs.add(job);
            return true;
        }
        return false;
    }

    /** Get the peak memory usage of this processor
     *
     * @return the peak memory usage of the jobs assigned to this processor
     * */
    public int getPeakMemoryUsage() {
        int peakMemoryUsage = 0;

        for(Job job: jobs){
            peakMemoryUsage = Math.max(peakMemoryUsage, job.getMemoryUsage());
        }
        return peakMemoryUsage;
    }

    /** Get the total computation (execution) time of this processor
     *
     * @return the total computation (execution) time of jobs assigned
     * to this processor
     */
    public int getTotalComputationTime() {
        int totalCompletionTime = 0;

        for(Job job: jobs){
            totalCompletionTime += job.getExecutionTime();
        }
        return totalCompletionTime;
    }

    /** Check if this processor is equal to a given processor
     *
     * @return true if both processors have exactly the same jobs,
     * in the same order, and they have the same time limit
     */
    public boolean equals(Processor that) {

        if (that == null) {
            return false;
        }
        if (this.timeLimit != that.timeLimit) {
            return false;
        }
        if (!this.jobs.equals(that.jobs)) {
            return false;
        }
        return true;
    }

    /** Get the time limit of this processor
     *
     * @return the time limit on this processor
     */
    public int getTimeLimit() {
        return this.timeLimit;
    }

    /**
     * Obtain the jobs scheduled on this processor, in scheduled order
     * @return the jobs scheduled on this processor, in scheduled order
     */
    public Job[] getJobs() {
        List<Job> jobsList = new ArrayList<>(jobs);
        jobsList.sort(Comparator.comparing(Job::getExecutionTime));
        return jobsList.toArray(new Job[0]);
    }
}