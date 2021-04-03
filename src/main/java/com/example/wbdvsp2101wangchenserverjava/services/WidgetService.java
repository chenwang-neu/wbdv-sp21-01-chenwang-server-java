package com.example.wbdvsp2101wangchenserverjava.services;

import com.example.wbdvsp2101wangchenserverjava.models.Widget;
import com.example.wbdvsp2101wangchenserverjava.repositories.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WidgetService {

    @Autowired
    WidgetRepository repository;

    // implement crud operations
    public Widget createWidgetForTopic(String topicId, Widget widget) {
        widget.setTopicId(topicId);
        return repository.save(widget);
    }
    public List<Widget> findAllWidgets() {
        return (List<Widget>) repository.findAll();
        //return widgets;
    }

    public List<Widget> findWidgetsForTopic(String topicId) {
        return repository.findWidgetsForTopic(topicId);
    }

    public Widget findWidgetById(Long id) {
        return repository.findById(id).get();
    }

    public Integer updateWidget(Long id, Widget newWidget) {
        if(repository.findById(id).isPresent()) {
            Widget originalWidget = findWidgetById(id);

            originalWidget.setText(newWidget.getText());
            originalWidget.setText(newWidget.getText());
            originalWidget.setTopicId(newWidget.getTopicId());
            originalWidget.setType(newWidget.getType());
            originalWidget.setText(newWidget.getText());
            originalWidget.setSize(newWidget.getSize());
            originalWidget.setWidgetOrder(newWidget.getWidgetOrder());
            originalWidget.setWidth(newWidget.getWidth());
            originalWidget.setHeight(newWidget.getHeight());
            originalWidget.setSrc(newWidget.getSrc());
            originalWidget.setCssClass(newWidget.getCssClass());
            originalWidget.setStyle(newWidget.getStyle());
            originalWidget.setName(newWidget.getName());
            originalWidget.setValue(newWidget.getValue());

            repository.save(originalWidget);
            return 1;
        }
        return -1;
    }

    public Integer deleteWidget(Long id) {
        repository.deleteById(id);
        return 1;
    }
}
