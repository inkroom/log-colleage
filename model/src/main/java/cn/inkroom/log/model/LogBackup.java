package cn.inkroom.log.model;

import java.util.Date;

/**
 * 日志文件
 * @author 墨盒
 * @date 19-2-8
 */
public class LogBackup {
    private int id;
    private String path;
    private Date created;
    private Date start;
    private Date end;
    private long size;//包含日志条数
    private long length;//文件字节数

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "LogBackup{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", created=" + created +
                ", start=" + start +
                ", end=" + end +
                ", size=" + size +
                ", length=" + length +
                '}';
    }
}
