import React from 'react';
import { mount } from 'enzyme';
import Index from '../index';

describe('index page', () => {
  it('should have IndexPage component', () => {
    const subject = mount(<Index />);

    expect(subject.find('IndexPage')).toHaveLength(1);
  });
});
