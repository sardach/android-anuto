package ch.bfh.anuto.util.iterator;

import java.util.Iterator;

public abstract class StreamIterator<T> implements Iterator<T> {

    public abstract void close();

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }


    public T first() {
        T first = null;

        if (this.hasNext()) {
            first = this.next();
        }

        this.close();
        return first;
    }

    public T last() {
        T last = null;

        while (this.hasNext()) {
            last = this.next();
        }

        this.close();
        return last;
    }

    public T min(Function<? super T, Float> scoreFunction) {
        T minObject = null;
        float minValue = 0f;

        while(this.hasNext()) {
            T object = this.next();
            float value = scoreFunction.apply(object);

            if (minObject == null || value < minValue) {
                minObject = object;
                minValue = value;
            }
        }

        this.close();

        return minObject;
    }

    public T max(Function<? super T, Float> scoreFunction) {
        T maxObject = null;
        float maxValue = 0f;

        while(this.hasNext()) {
            T object = this.next();
            float value = scoreFunction.apply(object);

            if (maxObject == null || value > maxValue) {
                maxObject = object;
                maxValue = value;
            }
        }

        this.close();

        return maxObject;
    }


    public StreamIterator<T> filter(Predicate<? super T> filter) {
        return new FilteringIterator<>(this, filter);
    }

    public <F> StreamIterator<F> transform(Function<? super T, ? extends F> transformation) {
        return new TransformingIterator<>(this, transformation);
    }

    public <F> StreamIterator<F> cast(final Class<F> castTo) {
        return new TransformingIterator<>(this, new Function<T, F>() {
            @Override
            public F apply(T input) {
                return castTo.cast(input);
            }
        });
    }
}