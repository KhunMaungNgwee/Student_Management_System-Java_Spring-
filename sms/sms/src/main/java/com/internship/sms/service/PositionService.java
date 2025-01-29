/**
 * 
 */
package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.Position;

/**
 * 
 */
public interface PositionService {
public List<Position> getAll();

public Position getPositionById(Long id);

public Position create(Position position);

public Position update(Position position);

public boolean delete(Position position);

public Position checkByName(String name);
}
