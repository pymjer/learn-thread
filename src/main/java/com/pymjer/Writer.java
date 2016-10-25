package com.pymjer;

class Writer extends Thread {
    private IBuffer buff;

    public Writer(IBuffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        buff.write();
    }
}