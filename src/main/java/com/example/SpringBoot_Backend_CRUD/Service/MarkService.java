package com.example.SpringBoot_Backend_CRUD.Service;

import com.example.SpringBoot_Backend_CRUD.DTO.MarkDTO;
import com.example.SpringBoot_Backend_CRUD.Entity.Course;
import com.example.SpringBoot_Backend_CRUD.Entity.Mark;

import java.util.List;

public interface MarkService {
    public MarkDTO AddMark(Mark mark) ;
    public MarkDTO findMark(int id) ;
    public void RemoveMark(int id) ;
    public MarkDTO UpdateMark(int id , Mark mark);
    public List<MarkDTO> findAll() ;
}
