package algorithm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class GozintaChains {

    private LinkedList<LinkedList<Long>> [] branchesSaved;
    private LinkedList<LinkedList<Long>> gozintaChain;
    private long numberOfOperations;
    private long numberOfRecursiveCalls;

    public GozintaChains(long number) {
        numberOfOperations = 0;
        numberOfRecursiveCalls = -1;
        gozintaChain = new LinkedList<>();
        createChains(number);
    }

    public GozintaChains() {
        numberOfOperations = 0;
        numberOfRecursiveCalls = -1;
        gozintaChain = new LinkedList<>();
    }

    public LinkedList<LinkedList<Long>> getGozintaChain() {
        return gozintaChain;
    }

    public LinkedList<LinkedList<Long>>[] getBranchesSaved() {
        return branchesSaved;
    }

    public long getNumberOfOperations() {
        return numberOfOperations;
    }

    public long getNumberOfRecursiveCalls() {
        return numberOfRecursiveCalls;
    }

    public long getNumberOfChains() {
        return gozintaChain.size();
    }

    public LinkedList<LinkedList<Long>> createChains(long n){
        branchesSaved =  (LinkedList<LinkedList<Long>>[])new LinkedList<?>[(int)n+1];
        LinkedList<Long> firstList = new LinkedList<>();
        firstList.addFirst(n);
        gozintaChain.addFirst(new LinkedList<>(firstList));
        if (n>1){
            gozintaAlgorithm(n);
        }
        return gozintaChain;
    }

    private void gozintaAlgorithm(long currentNumber){
        //numberOfRecursiveCalls++;
        LinkedList<LinkedList<Long>> singleBranchToSave = new LinkedList<>();
        if (currentNumber != 1){
            long squareRoot = (long)Math.sqrt(currentNumber);
            for(long i = squareRoot; i >= 1 ; i--){
                //numberOfOperations++;
                if(isDivisible(currentNumber, i)) {
                    addNewListToBranch (currentNumber, singleBranchToSave);
                    LinkedList<Long> currentChainCopy = new LinkedList<>(gozintaChain.getFirst());
                    long newQuotient = currentNumber / i;
                    if (newQuotient != currentNumber) {
                        addBranchNumbers(newQuotient, singleBranchToSave);
                        if (newQuotient != i){
                            addNewListToChainAndBranch (currentNumber, singleBranchToSave, currentChainCopy);
                        }
                    }
                    if(newQuotient != i){
                        addBranchNumbers(i, singleBranchToSave);
                    }
                    if(i != 1){
                        addNewListToChain(currentChainCopy);
                    }
                }
            }
            saveBranch((int)currentNumber, singleBranchToSave);
        }else{
            singleBranchToSave.addFirst(new LinkedList<>(Arrays.asList(1L)));
            saveBranch((int)currentNumber, singleBranchToSave);
        }
    }

    private void saveBranch (int index, LinkedList<LinkedList<Long>> singleBranchToSave){
        if(!branchIsSaved(index)) { branchesSaved[index] = singleBranchToSave;}
    }

    private boolean isDivisible (long dividend, long divisor){
        return dividend%divisor==0;
    }

    private boolean branchIsSaved (int branchNumber){
        return branchesSaved[branchNumber] != null;
    }

    private void joinSavedBranchToCurrentChain (long branchNumber){
        if (branchesSaved[(int)branchNumber].size()>1){
            Iterator<LinkedList<Long>> branchIterator = branchesSaved[(int)branchNumber].iterator();
            while (branchIterator.hasNext()) {
                LinkedList<Long> singleChainOfBranch = branchIterator.next();
                LinkedList<Long> gozintaChainCopy = new LinkedList<>(gozintaChain.getFirst());
                gozintaChain.getFirst().addAll(0, singleChainOfBranch);
                if (branchIterator.hasNext()) {
                    gozintaChain.addFirst(gozintaChainCopy);
                }
            }
        }else{
            LinkedList<Long> singleChainOfBranch = branchesSaved[(int)branchNumber].getFirst();
            gozintaChain.getFirst().addAll(0, singleChainOfBranch);
        }
    }

    private void joinSavedBranchToCurrentBranch
            (long branchNumber, LinkedList<LinkedList<Long>> singleBranchToSave){
        if (branchesSaved[(int)branchNumber].size()>1) {
            Iterator<LinkedList<Long>> branchIterator = branchesSaved[(int) branchNumber].iterator();
            while (branchIterator.hasNext()) {
                LinkedList<Long> singleChainOfBranch = branchIterator.next();
                LinkedList<Long> branchListCopy = new LinkedList<>(singleBranchToSave.getFirst());
                singleBranchToSave.getFirst().addAll(0, singleChainOfBranch);
                if (branchIterator.hasNext()) {
                    singleBranchToSave.addFirst(branchListCopy);
                }
            }
        }else{
            LinkedList<Long> singleChainOfBranch = branchesSaved[(int)branchNumber].getFirst();
            singleBranchToSave.getFirst().addAll(0, singleChainOfBranch);
        }
    }

    private void joinSavedBranchToCurrentBranchAndChain
            (long branchNumber, LinkedList<LinkedList<Long>> singleBranchToSave){
        if (branchesSaved[(int)branchNumber].size()>1) {
            Iterator<LinkedList<Long>> branchIterator = branchesSaved[(int) branchNumber].iterator();
            while (branchIterator.hasNext()) {
                LinkedList<Long> singleChainOfBranch = branchIterator.next();
                LinkedList<Long> branchListCopy = new LinkedList<>(singleBranchToSave.getFirst());
                LinkedList<Long> gozintaChainCopy = new LinkedList<>(gozintaChain.getFirst());
                singleBranchToSave.getFirst().addAll(0, singleChainOfBranch);
                gozintaChain.getFirst().addAll(0, singleChainOfBranch);
                if (branchIterator.hasNext()) {
                    singleBranchToSave.addFirst(branchListCopy);
                    gozintaChain.addFirst(gozintaChainCopy);
                }
            }
        }else{
            LinkedList<Long> singleChainOfBranch = branchesSaved[(int)branchNumber].getFirst();
            singleBranchToSave.getFirst().addAll(0, singleChainOfBranch);
            gozintaChain.getFirst().addAll(0, singleChainOfBranch);
        }
    }

    private void addBranchNumbers
            (long newQuotient, LinkedList<LinkedList<Long>> singleBranchToSave){
        if (branchIsSaved((int)newQuotient)){
            joinSavedBranchToCurrentBranchAndChain(newQuotient, singleBranchToSave);
        }else{
            gozintaChain.getFirst().addFirst(newQuotient);
            gozintaAlgorithm(newQuotient);
            if(branchIsSaved((int)newQuotient)){
                joinSavedBranchToCurrentBranch(newQuotient, singleBranchToSave);
            }
        }
    }

    private void addNewListToChainAndBranch
            (long currentNumber, LinkedList<LinkedList<Long>> singleBranchToSave, LinkedList<Long> currentChainCopy){
        gozintaChain.addFirst(new LinkedList<>(currentChainCopy));
        singleBranchToSave.addFirst(new LinkedList<>(Arrays.asList(currentNumber)));
    }

    private void addNewListToBranch
            (long currentNumber, LinkedList<LinkedList<Long>> singleBranchToSave){
        singleBranchToSave.addFirst(new LinkedList<>(Arrays.asList(currentNumber)));
    }

    private void addNewListToChain(LinkedList<Long> currentChainCopy){
        gozintaChain.addFirst(new LinkedList<>(currentChainCopy));
    }
}
