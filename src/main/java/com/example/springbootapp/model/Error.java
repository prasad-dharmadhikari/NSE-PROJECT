package com.example.springbootapp.model;

import com.google.gson.annotations.Expose;

public class Error {
	
	@Expose
    public String message;
    
	//@JsonIgnore
    public String id;
	//@JsonIgnore
    public String item;
	//@JsonIgnore
    public String file;
	
	//@JsonIgnore
    public Integer line;
	//@JsonIgnore
    public Integer col;
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public Integer getLine() {
        return line;
    }
    public void setLine(int line) {
        this.line = line;
    }
    public Integer getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    @Override
    public String toString() {
        return "Error [message=" + message + ", id=" + id + ", item=" + item + ", file=" + file + ", line=" + line
                + ", col=" + col + "]";
    }
    
}
