package com.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Comparators;

public class AssignmentForComplexJson {

	public static void main(String args[])
	{
		List<Integer> rgba=new ArrayList<Integer>(Arrays.asList(255,255,255,1));
		Map<String,Object> code=new HashMap<>(); 
		code.put("rgba",rgba);
		code.put("hex", "#000");

		HashMap<Object,Object> firstobj=new HashMap<Object,Object>();
        firstobj.put("color","black");
		firstobj.put("category","hue");
		firstobj.put("type", "primary");
		firstobj.put("code",code);


		List<Integer> rgba2=new ArrayList<Integer>(Arrays.asList(0,0,0,1));
		Map<String,Object> code2=new HashMap<>(); 
		code.put("rgba",rgba2);
		code.put("hex", "#FFF");

		HashMap<Object,Object> secondobj=new HashMap<>();
		secondobj.put("color","white");
		secondobj.put("category","value");
		secondobj.put("code",code2);

		List<HashMap<Object,Object>> colors= new ArrayList<HashMap<Object,Object>>();
		colors.add(firstobj);
		colors.add(secondobj);

		HashMap<Object,Object> finalobject= new HashMap<Object,Object>();
		finalobject.put("colors",colors);




	}



}
