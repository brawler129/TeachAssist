package com.suchana.atten.rest;


import com.suchana.atten.models.Student;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("initial_student_list/{username}")
    Call<Map<String, List<Student>>> getInitialStudentList(@Path("username") String username);

    @PUT("update_student_attendance")
    Call<ResponseBody> uploadStudentAttendance(@Body Map<String,List<Student>> studentsPerSubject);
}