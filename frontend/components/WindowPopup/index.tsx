import React from 'react';
import _ from 'lodash';

interface WindowSpec {
  left?: number; // + screenX
  top?: number; // + screenY
  height?: number; // + innerHeight
  width?: number; // + innerWidth
  centerscreen?: boolean;
  outerHeight?: number;
  outerWidth?: number;
  menubar?: boolean;
  toolbar?: boolean;
  location?: boolean;
  personalbar?: boolean;
  // directories?: boolean; // deprecated
  status?: boolean;
  channelmode?: boolean;
  fullscreen?: boolean;
  resizable?: boolean;
  scrollbars?: boolean;
  titlebar?: boolean;
}

const convertBoolean = (bool: boolean): string => (bool ? 'yes' : 'no');

const pushSpec = (specs: string[], value: any, key: string[] | string) => {
  if (!_.isNil(value)) {
    const keys = _.isArray(key) ? key : [key];
    keys.map((k) => specs.push(`${k}=${_.isBoolean(value) ? convertBoolean(value) : value}`));
  }
};

const makeSpec = (spec?: WindowSpec): string | undefined => {
  if (spec) {
    const specs: string[] = [];
    pushSpec(specs, spec.left, 'screenX');
    pushSpec(specs, spec.top, 'screenY');
    pushSpec(specs, spec.height, 'innerHeight');
    pushSpec(specs, spec.width, 'innerWidth');
    for (const [key, value] of Object.entries(spec)) {
      pushSpec(specs, value, key);
    }

    return specs.length ? specs.join(',') : undefined;
  }

  return undefined;
};

interface Props {
  url: string;
  name: '_blank' | '_parent' | '_self' | '_top' | string;
  specs?: WindowSpec;
  replace?: boolean;
}

/**
 * @param name `'_blank'` | `'_parent'` | `'_self'` | `'_top'` | `string`
 */
export const WindowPopup: React.FunctionComponent<Props> = ({ url, name, specs, replace }) => {
  // window.open(url, name, makeSpec(specs), replace);
  console.log(specs, replace, makeSpec(specs));

  return (
    <>
      <p>{url}</p>
      <p>{name}</p>
      {/* <p>{makeSpec(specs)}</p>
      <p>{convertBoolean(replace || false)}</p> */}
    </>
  );
};
