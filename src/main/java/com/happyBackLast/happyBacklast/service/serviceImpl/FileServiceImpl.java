package com.happyBackLast.happyBacklast.service.serviceImpl;

import com.happyBackLast.happyBacklast.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final String SUPABASE_URL = "https://xdzpatvesyfupvnkqfai.supabase.co";
    private final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InhkenBhdHZlc3lmdXB2bmtxZmFpIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc3NjA5MzA4NCwiZXhwIjoyMDkxNjY5MDg0fQ.QhWI40i5w0RmioJrZ8gGMOFVOdhQS1iy021uKLLUKlc";
    private final String BUCKET = "happyBacVideos";

    public String uploadVideo(MultipartFile file) {

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            String uploadUrl =
                    SUPABASE_URL + "/storage/v1/object/" + BUCKET + "/" + fileName;

            HttpURLConnection conn =
                    (HttpURLConnection) new URL(uploadUrl).openConnection();

            conn.setRequestMethod("PUT");

            conn.setDoOutput(true);

            conn.setRequestProperty("Authorization", "Bearer " + SUPABASE_KEY);
            conn.setRequestProperty("apikey", SUPABASE_KEY);
            conn.setRequestProperty("Content-Type", file.getContentType());

            try (OutputStream os = conn.getOutputStream()) {
                os.write(file.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();

            if (responseCode == 200 || responseCode == 201) {
                return SUPABASE_URL
                        + "/storage/v1/object/public/"
                        + BUCKET + "/"
                        + fileName;
            } else {
                throw new RuntimeException("Upload failed: " + responseCode);
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload error: " + e.getMessage());
        }
    }
}