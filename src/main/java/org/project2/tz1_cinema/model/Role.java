package org.project2.tz1_cinema.model;

/**
 * Enum representing the roles available in the cinema application.
 * Roles include ADMIN and USER, which determine the level of access and permissions within the system.
 */
public enum Role {
    /**
     * Role with administrative privileges, allowing management of movies, users, and system settings.
     */
    ADMIN,

    /**
     * Regular user role with access to view movies, add comments, and manage personal account details.
     */
    USER
}
