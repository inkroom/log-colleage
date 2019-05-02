package cn.inkroom.log.web.bean;

import java.util.Date;

/**
 * @author 墨盒
 * @date 19-5-2
 */
public class DownloadLog {

    private int id;
    private String username;
    private String file;
    private Date start;
    private Date end;
    private Date createdAt;
    private long size;

    @Override
    public String toString() {
        return "DownloadLog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", file='" + file + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", createdAt=" + createdAt +
                ", size=" + size +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
