/*
 * Copyright [2011-2016] "Neo Technology"
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 *
 */
package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import school.domain.Student;
import school.repository.StudentRepository;

@RestController
@RequestMapping(value = "/api/students", consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

	private StudentRepository studentRepository;

	@Autowired
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Student> readAll() {
		return studentRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Student create(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Student read(@PathVariable Long id) {
		return studentRepository.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		studentRepository.delete(id);
	}

	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Student update(@PathVariable Long id, @RequestBody Student update) {
		final Student existing = studentRepository.findOne(id);
		existing.updateFrom(update);
		return studentRepository.save(existing);
	}
}
