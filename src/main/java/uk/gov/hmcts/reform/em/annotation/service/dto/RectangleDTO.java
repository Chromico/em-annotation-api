package uk.gov.hmcts.reform.em.annotation.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Rectangle entity.
 */
public class RectangleDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private Double x;

    private Double y;

    private Double width;

    private Double height;

    private Long annotationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Long getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(Long annotationId) {
        this.annotationId = annotationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RectangleDTO rectangleDTO = (RectangleDTO) o;
        if (rectangleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rectangleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RectangleDTO{" +
            "id=" + getId() +
            ", x=" + getX() +
            ", y=" + getY() +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", annotation=" + getAnnotationId() +
            "}";
    }
}
