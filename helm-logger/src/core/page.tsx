import React from 'react';
import ReactDOMServer from 'react-dom/server';
import Convert from 'ansi-to-html';

const convert = new Convert();

const Style: React.FC<{}> = () => {
  return (
    <style
      dangerouslySetInnerHTML={{
        __html: `
          @font-face {
            font-family: "SFMono-Light";
            font-weight: 200;
            src: url("https://sf.abarba.me/SFMono-Light.otf");
          }
          
          body {
            font-family: SFMono-Light, -apple-system, system-ui, BlinkMacSystemFont;
            margin: 0;
            background-color: #24292e;
          }

          ul {
            list-style: none;
            padding: 5px;
            display: flex;
            flex-direction: column;
          }

          li {
            display: flex;
            flex-direction: row;
            font-size: 0.8rem;
          }

          li > a {
            color: #959da5;
            font-weight: normal;
            padding-right: 1em;
            text-decoration: none;
            width: 0.2em;
            text-align: right;
          }
          
          li > a:hover {
            text-decoration: underline;
          }

          li > span.timestamp {
            width: 10em;
            color: #959da5;
            position: relative;
            white-space: nowrap;
            overflow: hidden;
          }

          li > span.timestamp:hover {
            white-space: initial;
            width: 16em;
          }

          li > p.log {
            margin: 0;
            color: #f6f8fa;
            left: 1em;
            position: relative;
            width: 50em;
          }`,
      }}
    />
  );
};

interface LogProps {
  index: number;
  log: Log;
}

const Log: React.FC<LogProps> = (props: LogProps) => {
  const { index, log } = props;
  const { text, timestamp } = log;
  const html = convert.toHtml(text);
  return (
    <li id={timestamp}>
      <a href={`#${timestamp}`}>{index}</a>
      <span className="timestamp">{timestamp}</span>
      <p className="log" dangerouslySetInnerHTML={{ __html: html }} />
    </li>
  );
};

export const renderLogs = (logs: Log[]): string => {
  return ReactDOMServer.renderToStaticMarkup(
    <>
      <Style />
      <ul>
        {logs.map((log, index) => (
          <Log key={log.timestamp} log={log} index={index + 1} />
        ))}
      </ul>
    </>,
  );
};
