package io.github.lowering.common.domain;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/19.
 */
@MappedSuperclass
public abstract class Tree<T extends  Tree<T>> extends Id {

    @ManyToOne
    @JoinColumn(name = "parent")
    private T parent;
    @OneToMany(mappedBy = "parent")
    private Set<T> children = new HashSet<>(0);

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public Set<T> getChildren() {
        return children;
    }

    public void setChildren(Set<T> children) {
        this.children = children;
    }
}
