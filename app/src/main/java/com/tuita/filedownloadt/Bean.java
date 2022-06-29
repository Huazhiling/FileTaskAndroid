package com.tuita.filedownloadt;

public class Bean {
    private String name;
    private String url;
    private String src;
    private String kbProcess;
    private int process = 0;
    private int maxProcess = 100;

    public String getKbProcess() {
        return kbProcess;
    }

    public void setKbProcess(String kbProcess) {
        this.kbProcess = kbProcess;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getMaxProcess() {
        return maxProcess;
    }

    public void setMaxProcess(int maxProcess) {
        this.maxProcess = maxProcess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
