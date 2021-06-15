package com.fracta.james.domain.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fracta.james.domain.models.MessagePlaceholder;

@Entity
@Table(name = "messages")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_seq")
	@SequenceGenerator(name = "messages_seq", sequenceName = "messages_id_seq", allocationSize = 1)
	private long id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String desc;
	
	@Column(length = 2048, nullable = false)
	private String text;

	public Message() {
		
	}
	
	public void applyPlaceholder(MessagePlaceholder placeholder) {
		text = text.replace(placeholder.getPlaceholder(), placeholder.getReplacement().toString());
	}
	
	public void removePlaceholders() {
		text = text.replaceAll("%.*%", "");
	}
	
	public void removeTextBetweenPlaceholder(String placeholderName) {
		text = text.replace(placeholderName + "(?s).*" + placeholderName, "");
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, desc, text);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		var other = (Message) o;
		return Objects.equals(id, other.id)
				&& Objects.equals(name, other.name)
				&& Objects.equals(desc, other.desc)
				&& Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "Message{id=" + id
				+ ", name=" + name
				+ ", desc=" + desc 
				+ ", text=" + text 
				+ "}";
	}
	
	
	
	
}