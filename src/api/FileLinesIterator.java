package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class FileLinesIterator implements Iterator<String>, AutoCloseable {
    private BufferedReader br;
    private String nextLine;

    public FileLinesIterator(String filePath) throws IOException {
        br = new BufferedReader(new FileReader(filePath));
        fetchNextLine();
    }

    private void fetchNextLine() {
        try {
            nextLine = br.readLine();
        } catch (IOException e) {
            // Handle IOException
            e.printStackTrace();
            nextLine = null;
        }
    }

    @Override
    public boolean hasNext() {
        return nextLine != null;
    }

    @Override
    public String next() {
        String line = nextLine;
        fetchNextLine();
        return line;
    }

    @Override
    public void close() throws Exception {
        if (br != null) { // make sure br is not null before closing it
            br.close();
        }
    }
}
