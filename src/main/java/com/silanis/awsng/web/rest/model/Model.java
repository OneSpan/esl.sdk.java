package com.silanis.awsng.web.rest.model;
//
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.*;

@JsonFilter("DirtyFieldsFilter")
public class Model implements java.io.Serializable
{

   // Empty Constructor
   public Model ( ) {}

   @JsonIgnore
   private Set<String> dirtyFields = new HashSet<String>();

   @JsonIgnore
   protected void setDirty(String field) {
       dirtyFields.add(field);
   }

   @JsonIgnore
   public boolean isDirty(String field) {
       return dirtyFields.contains(field);
   }

   @JsonIgnore
   public Set<String> getDirtyFields() {
       return dirtyFields;
   }


   public void clean() {
       dirtyFields.clear();
   }

}