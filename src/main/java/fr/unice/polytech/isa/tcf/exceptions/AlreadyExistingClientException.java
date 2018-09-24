package fr.unice.polytech.isa.tcf.exceptions;

import java.io.Serializable;

public class AlreadyExistingClientException extends Exception implements Serializable {

    private String conflictingName;

    public AlreadyExistingClientException(String name) {
        super(name);
        conflictingName = name;
    }

    public String getConflictingName() {
        return conflictingName;
    }

    public void setConflictingName(String conflictingName) {
        this.conflictingName = conflictingName;
    }


}
