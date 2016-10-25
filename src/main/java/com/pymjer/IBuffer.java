package com.pymjer;

public interface IBuffer {
	public void write();
    public void read() throws InterruptedException;
}